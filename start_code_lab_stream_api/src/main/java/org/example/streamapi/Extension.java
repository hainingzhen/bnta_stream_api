package org.example.streamapi;

import org.example.streamapi.model.Bodybuilder;
import org.example.streamapi.model.Friend;
import org.example.streamapi.model.User;

import javax.swing.text.html.Option;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public class Extension {
    /*
        Given two int numbers a and b, return int [] with values that are in the range between smaller and bigger arg:
        - use IntStream.range
        - swap the argument's values without introducing a new, local variable.
    */
    public int [] streamNumbers(int a, int b) {

        return IntStream.range(Math.min(a, b), Math.max(a, b)).sorted().toArray();
    }

    /*
        Given a list of users, return a User with the given user id. If there is no user with this id,
        return new user with the name "New user", given id, gender "unknown".

        (use Optional API)
    */
    public User getUserByIdOrCreateNew(List<User> users, long userId) {

        Optional<User> userOptional = users.stream().filter(user -> user.getId() == userId).findFirst();

        return userOptional.orElse(new User(userId, "New user", User.GENDER.UNKNOWN));
    }

    /*
        Given List<Friend>, filter the ones who are available on Saturday and want to party:
        - getAvailableDay returns "Saturday" and
        - getActivity returns "Party"
        - define predicates and use '.and' method.
    */

    public List<String> partyWithFriends(List<Friend> friends) {

        Predicate<Friend> day = friend -> friend.getAvailableDay().equals("Saturday");
        Predicate<Friend> activity = friend -> friend.getActivity().equals("Party");

        return friends.stream().filter(day.and(activity)).map(friend -> friend.getName()).toList();
    }

    /*
        Return names of sorted bodybuilders.

        Sort List<Bodybuilder> using your custom comparator.

        Write a comparator for type BodyBuilder that will sort bodybuilders by:
        - who can lift more,
        - then who is younger,
        - then name alphabetically.
     */
    public List<String> sortBodybuilders(List<Bodybuilder> bodybuilders) {

        Comparator<Bodybuilder> stronger = (bodybuilder2, bodybuilder1) -> Integer.compare(bodybuilder1.getLift(), bodybuilder2.getLift());
        Comparator<Bodybuilder> younger = (bodybuilder1, bodybuilder2) -> Integer.compare(bodybuilder1.getAge(), bodybuilder2.getAge());
        Comparator<Bodybuilder> alphabetically = (bodybuilder1, bodybuilder2) -> bodybuilder1.getName().compareTo(bodybuilder2.getName());

        return bodybuilders
                .stream()
                .sorted(stronger.thenComparing(younger).thenComparing(alphabetically))
                .map(bodybuilder -> bodybuilder.getName())
                .toList();
    }

}
