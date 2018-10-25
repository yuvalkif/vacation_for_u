package Logger;

import javafx.stage.Stage;

import java.util.Stack;

/**
 * singletone class to hold stages so different controllers can open and close stages from
 * previous controllers , and so maintain sequential flow between them.
 */

public class StageHolder {

    private static StageHolder ourInstance = new StageHolder();

    public static StageHolder getInstance() {
        return ourInstance;
    }

    private Stack<Stage> stageStack = new Stack<>();

    private StageHolder() {

    }

    /**
     * submit a stage to hold
     * @param stage stage to hold
     */
    public void holdStage(Stage stage){
        stageStack.push(stage);
    }

    /**
     * get the last stage
     * @return the previous stage in a sequential order
     */
    public Stage getStage(){
        return stageStack.pop();
    }
}
