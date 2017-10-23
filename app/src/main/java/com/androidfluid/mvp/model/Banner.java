package com.androidfluid.mvp.model;

/**
 * Created by singh on 21/10/17.
 */

public class Banner {
    private String headerText;
    private String desctiptionText;
    private int LImageUrl;
    private int RImageUrl;

    public String getHeaderText() {
        return headerText;
    }

    public void setHeaderText(String headerText) {
        this.headerText = headerText;
    }

    public String getDesctiptionText() {
        return desctiptionText;
    }

    public void setDesctiptionText(String desctiptionText) {
        this.desctiptionText = desctiptionText;
    }

    public int getLImageUrl() {
        return LImageUrl;
    }

    public void setLImageUrl(int LImageUrl) {
        this.LImageUrl = LImageUrl;
    }

    public int getRImageUrl() {
        return RImageUrl;
    }

    public void setRImageUrl(int RImageUrl) {
        this.RImageUrl = RImageUrl;
    }
}
