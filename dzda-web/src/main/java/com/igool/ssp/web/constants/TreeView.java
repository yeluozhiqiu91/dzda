package com.igool.ssp.web.constants;

import java.util.List;

public class TreeView {


        private Integer id;
        private Integer pId;
        private String url;
        private String name;
        private Boolean checked;
        //private Boolean isParent;
        private List<TreeView> nodes;
        public Boolean getChecked() {
        return checked;
    }

        public void setChecked(Boolean checked) {
        this.checked = checked;
        }

        public Integer getId() {
            return id;
        }
        public void setId(Integer id) {
            this.id = id;
        }
        public Integer getpId() {
            return pId;
        }
        public void setpId(Integer id) {
            pId = id;
        }
        public String getUrl() {
            return url;
        }
        public void setUrl(String url) {
            this.url = url;
        }
        public List<TreeView> getNodes() {
            return nodes;
        }
        public void setNodes(List<TreeView> nodes) {
            this.nodes = nodes;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    /*public Boolean getIsParent() {
        return isParent;
    }
    public void setIsParent(Boolean isParent) {
        this.isParent = isParent;
    }*/



}
