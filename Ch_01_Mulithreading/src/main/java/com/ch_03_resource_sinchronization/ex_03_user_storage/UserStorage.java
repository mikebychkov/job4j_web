package com.ch_03_resource_sinchronization.ex_03_user_storage;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@ThreadSafe
public class UserStorage {

    @GuardedBy("this")
    private ConcurrentMap<Integer, User> users = new ConcurrentHashMap<>();

    public UserStorage() {
    }

    public synchronized boolean add(User user) {
        users.putIfAbsent(user.getId(), User.of(user.getId(), user.getAmount()));
        return true;
    }

    public synchronized boolean update(User user) {
        users.replace(user.getId(), User.of(user.getId(), user.getAmount()));
        return true;
    }

    public synchronized boolean delete(User user) {
        users.remove(user.getId());
        return true;
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        User uFrom = users.get(fromId);
        User uTo = users.get(toId);
        if (uFrom == null || uTo == null) {
            return false;
        }
        if (uFrom.getAmount() < amount) {
            return false;
        }
        uFrom.setAmount(uFrom.getAmount() - amount);
        uTo.setAmount(uTo.getAmount() + amount);
        return true;
    }
}
