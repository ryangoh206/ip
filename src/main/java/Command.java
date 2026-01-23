public enum Command {
    BYE, LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT;

    public static Command parse(String input) {
        for (Command c: values()) {
            if (input.equalsIgnoreCase(c.name())) {
                return c;
            }
        }
        throw new InvalidCommandException("I'm sorry, but I don't know what that means.");
    }
    
}
