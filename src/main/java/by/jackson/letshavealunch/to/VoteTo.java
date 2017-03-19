package by.jackson.letshavealunch.to;

import by.jackson.letshavealunch.model.Restaurant;

public class VoteTo {
    private Restaurant restaurant;
    private int voteCount;
    private boolean UserVote;

    public VoteTo() {
    }

    public VoteTo(Restaurant restaurant, int voteCount, boolean userVote) {
        this.restaurant = restaurant;
        this.voteCount = voteCount;
        UserVote = userVote;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public boolean isUserVote() {
        return UserVote;
    }

    public void setUserVote(boolean userVote) {
        UserVote = userVote;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VoteTo voteTo = (VoteTo) o;

        if (voteCount != voteTo.voteCount) return false;
        if (UserVote != voteTo.UserVote) return false;
        return restaurant != null ? restaurant.equals(voteTo.restaurant) : voteTo.restaurant == null;
    }

    @Override
    public int hashCode() {
        int result = restaurant != null ? restaurant.hashCode() : 0;
        result = 31 * result + voteCount;
        result = 31 * result + (UserVote ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "VoteTo{" +
                "restaurant=" + restaurant +
                ", voteCount=" + voteCount +
                ", UserVote=" + UserVote +
                '}';
    }
}
