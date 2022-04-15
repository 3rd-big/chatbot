package com.hecto.chatbot.domain;

import com.hecto.chatbot.repository.ReadFile;

import java.util.Objects;

public class Member {
    private String id;
    private String password;
    private String name;
    private int mobileNumber;

    private Member(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public static Member of(String id, String password) {
        return new Member(id, password);
    }

    public boolean duplicationCheck(String id) {
        ReadFile file = new ReadFile();
        file.ReadTextFile();
        file.saveData();

        if (this.id.equals(id)) return false;
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return id.equals(member.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
