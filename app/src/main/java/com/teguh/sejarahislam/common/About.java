package com.teguh.sejarahislam.common;

public class About {
    private int images;
    private int id;
    private String name;
    private String nim;
    private String email;
    private String job;

    public About(int images, int id, String name, String nim, String email, String job) {
        this.images = images;
        this.id = id;
        this.name = name;
        this.nim = nim;
        this.email = email;
        this.job = job;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getImages() {
        return images;
    }

    public void setImages(int images) {
        this.images = images;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
