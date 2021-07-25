package com.technototes.library.command;

/** Command with runnable
 * @author Alex Stedman
 */
public class OldInstantCommand extends OldCommand {
    private Runnable runnable;

    /** Make instant command
     *
     * @param runnable The runnable to run like a command
     */
    public OldInstantCommand(Runnable runnable) {
        this.runnable = runnable;
    }

    @Override
    public void execute() {
        runnable.run();
    }
}
