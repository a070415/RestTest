package com.example.hashmaptest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FreeData {

    public FreeData(String boardId, String boardType, String boardTitle, String boardContent) {
        this.boardId = boardId;
        this.boardType = boardType;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
    }

    @SerializedName("boardId")
    @Expose
    private String boardId;

    @SerializedName("boarType")
    @Expose
    private String boardType;

    @SerializedName("boardTitle")
    @Expose
    private String boardTitle;

    @SerializedName("boardContent")
    @Expose
    private String boardContent;


    public String getBoardId() {
        return boardId;
    }

    public void setBoardId(String boardId) {
        this.boardId = boardId;
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

    public String getBoardContent() {
        return boardContent;
    }

    public void setBoardContent(String boardContent) {
        this.boardContent = boardContent;
    }

    @Override
    public String toString() {
        return "Data{" +
                "boardId='" + boardId + '\'' +
                ", boardType='" + boardType + '\'' +
                ", boardTitle='" + boardTitle + '\'' +
                ", boardContent='" + boardContent + '\'' +
                '}';
    }
}
