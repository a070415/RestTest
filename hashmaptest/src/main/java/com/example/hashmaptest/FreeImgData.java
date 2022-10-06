package com.example.hashmaptest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FreeImgData {

    public FreeImgData(String imgId, String boardType, String boardTitle) {
        this.imgId = imgId;
        this.boardType = boardType;
        this.boardTitle = boardTitle;
    }

    @SerializedName("imgId")
    @Expose
    private String imgId;

    @SerializedName("boardType")
    @Expose
    private String boardType;

    @SerializedName("boardTitle")
    @Expose
    private String boardTitle;

    public String getImgId() {
        return imgId;
    }

    public void setImgId(String imgId) {
        this.imgId = imgId;
    }

    public String getBoardType() {
        return boardType;
    }

    public void setBoardType(String boardType) {
        this.boardType = boardType;
    }

    public String getBoardTitle() {
        return boardTitle;
    }

    public void setBoardTitle(String boardTitle) {
        this.boardTitle = boardTitle;
    }

    @Override
    public String toString() {
        return "FreeImgData{" +
                "imgId='" + imgId + '\'' +
                ", boardType='" + boardType + '\'' +
                ", boardTitle='" + boardTitle + '\'' +
                '}';
    }
}

