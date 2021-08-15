package com.example.madd_project;

public class Teacher_pdf_model {

    private String sub_name,Subject,Title_sub,edit_title,pdfUpload;
    private int update,delete;

    public String getSub_name() {
        return sub_name;
    }

    public void setSub_name(String sub_name) {
        this.sub_name = sub_name;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public String getTitle_sub() {
        return Title_sub;
    }

    public void setTitle_sub(String title_sub) {
        Title_sub = title_sub;
    }

    public String getEdit_title() {
        return edit_title;
    }

    public void setEdit_title(String edit_title) {
        this.edit_title = edit_title;
    }

    public String getPdfUpload() {
        return pdfUpload;
    }

    public void setPdfUpload(String pdfUpload) {
        this.pdfUpload = pdfUpload;
    }

    public int getUpdate() {
        return update;
    }

    public void setUpdate(int update) {
        this.update = update;
    }

    public int getDelete() {
        return delete;
    }

    public void setDelete(int delete) {
        this.delete = delete;
    }
}
