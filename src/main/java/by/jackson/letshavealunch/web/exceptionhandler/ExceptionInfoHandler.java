package by.jackson.letshavealunch.web.exceptionhandler;

import by.jackson.letshavealunch.util.exception.ErrorInfo;
import by.jackson.letshavealunch.util.exception.NotFoundException;
import by.jackson.letshavealunch.util.exception.VotingEndedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@ControllerAdvice(annotations = RestController.class)
public class ExceptionInfoHandler {
    private static final Logger LOG = LoggerFactory.getLogger(ExceptionInfoHandler.class);

    private static ErrorInfo logAndGetValidationErrorInfo(HttpServletRequest req, BindingResult result) {
        String[] details = result.getFieldErrors().stream()
                .map(fe -> fe.getField() + ' ' + fe.getDefaultMessage())
                .toArray(String[]::new);

        LOG.warn("Validation exception at request " + req.getRequestURL() + ": " + Arrays.toString(details));
        return new ErrorInfo(req.getRequestURL(), "ValidationException", details);
    }

    private static ErrorInfo logAndGetErrorInfo(HttpServletRequest req, Exception e, boolean logException) {
        if (logException) {
            LOG.error("Exception at request " + req.getRequestURL(), e);
        } else {
            LOG.warn("Exception at request " + req.getRequestURL() + ": " + e.toString());
        }
        return new ErrorInfo(req.getRequestURL(), e);
    }

    //  http://stackoverflow.com/a/22358422/548473
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY) // 422
    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    public ErrorInfo notFoundException(HttpServletRequest req, NotFoundException e) {
        return logAndGetErrorInfo(req, e, false);
    }

    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY) // 422
    @ExceptionHandler(VotingEndedException.class)
    @ResponseBody
    public ErrorInfo voteChangeNotPermittedException(HttpServletRequest req, VotingEndedException e) {
        return logAndGetErrorInfo(req, e, false);
    }

    @ResponseStatus(value = HttpStatus.CONFLICT)  // 409
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseBody
    public ErrorInfo conflict(HttpServletRequest req, DataIntegrityViolationException e) {
        return logAndGetErrorInfo(req, e, true);
    }

    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)  // 422
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public ErrorInfo bindValidationError(HttpServletRequest req, BindingResult result) {
        return logAndGetValidationErrorInfo(req, result);
    }

    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)  // 422
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ErrorInfo restValidationError(HttpServletRequest req, MethodArgumentNotValidException e) {
        return logAndGetValidationErrorInfo(req, e.getBindingResult());
    }

    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    public ErrorInfo actionDeniedError(HttpServletRequest req, AccessDeniedException e) {
        return logAndGetErrorInfo(req, e, true);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ErrorInfo handleError(HttpServletRequest req, Exception e) {
        return logAndGetErrorInfo(req, e, true);
    }
}
