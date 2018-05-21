package com.luis.strategy.datapackage.scene;

import java.io.Serializable;
import java.util.List;

/**
*
* @author Luis
*/
public class PreSceneListData implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<PreSceneData> preSceneDataList;

    public List<PreSceneData> getPreSceneDataList() {
        return preSceneDataList;
    }

    public void setPreSceneDataList(List<PreSceneData> preSceneDataList) {
        this.preSceneDataList = preSceneDataList;
    }

}
