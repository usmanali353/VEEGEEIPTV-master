package snsoftware.veegeeiptv.Model;

public class Episodes {
    private String id;
    private Integer episode_num;
    private String title;
    private String container_extension;
    private String customSid;
    private String added;
    private Integer season;
    private String directSource;
    private  String  movie_image;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getEpisodeNum() {
        return episode_num;
    }

    public void setEpisodeNum(Integer episodeNum) {
        this.episode_num = episodeNum;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContainer_extension() {
        return container_extension;
    }

    public void setContainer_extension(String container_extension) {
        this.container_extension = container_extension;
    }

    public String getCustomSid() {
        return customSid;
    }

    public void setCustomSid(String customSid) {
        this.customSid = customSid;
    }

    public String getAdded() {
        return added;
    }

    public void setAdded(String added) {
        this.added = added;
    }

    public Integer getSeason() {
        return season;
    }

    public void setSeason(Integer season) {
        this.season = season;
    }

    public String getDirectSource() {
        return directSource;
    }

    public void setDirectSource(String directSource) {
        this.directSource = directSource;
    }

    public String getMovie_image() {
        return movie_image;
    }

    public void setMovie_image(String movie_image) {
        this.movie_image = movie_image;
    }

    public Episodes(String id, String title, String container_extension, Integer season, String movie_image,Integer ep_num) {
        this.id = id;
        this.title = title;
        this.container_extension = container_extension;
        this.season = season;
        this.movie_image=movie_image;
        this.episode_num=ep_num;
    }
}
