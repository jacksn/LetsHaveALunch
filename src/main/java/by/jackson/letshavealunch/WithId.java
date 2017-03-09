package by.jackson.letshavealunch;

public interface WithId {
    Integer getId();

    void setId(Integer id);

    default boolean isNew() {
        return (getId() == null);
    }
}
