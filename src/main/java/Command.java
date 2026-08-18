import java.util.ArrayList;

/**
 * Represents an action the user can ask Duke to perform.
 */
public abstract class Command {
    /**
     * Executes this command on the task list.
     *
     * @param tasks task list to update or read
     */
    public abstract void execute(ArrayList<Task> tasks);

}
