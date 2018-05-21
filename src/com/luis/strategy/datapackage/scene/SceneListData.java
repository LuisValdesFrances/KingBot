package com.luis.strategy.datapackage.scene;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Luis
 */
public class SceneListData implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private List<SceneData> sceneDataList;

    public List<SceneData> getSceneDataList() {
        return sceneDataList;
    }

    public void setSceneDataList(List<SceneData> sceneDataList) {
        this.sceneDataList = sceneDataList;
    }
}
