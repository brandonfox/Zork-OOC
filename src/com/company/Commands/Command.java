package com.company.Commands;

public interface Command {
    void doCommand(String extraParam);

    String toString();
}
