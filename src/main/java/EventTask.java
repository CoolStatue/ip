public class EventTask extends Task{
    protected String from;
    protected String till;

    public EventTask(String description, String from, String till) {
        super(description);
        this.from = from;
        this.till = till;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from " + this.from
                + " to: " + this.till + ")";
    }
}
