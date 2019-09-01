package snsoftware.veegeeiptv.Model;

import java.util.List;

public class series {
    private Integer num;
    private String name;
    private Integer series_id;
    private String cover;
    private String plot;
    private String cast;
    private String director;
    private String genre;
    private String releaseDate;
    private String lastModified;
    private String rating;
    private Float rating5based;
    private List<String> backdropPath = null;
    private String youtubeTrailer;
    private String episodeRunTime;
    private String categoryId;

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSeriesId() {
        return series_id;
    }

    public void setSeriesId(Integer seriesId) {
        this.series_id = seriesId;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getLastModified() {
        return lastModified;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public Float getRating5based() {
        return rating5based;
    }

    public void setRating5based(Float rating5based) {
        this.rating5based = rating5based;
    }

    public List<String> getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(List<String> backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getYoutubeTrailer() {
        return youtubeTrailer;
    }

    public void setYoutubeTrailer(String youtubeTrailer) {
        this.youtubeTrailer = youtubeTrailer;
    }

    public String getEpisodeRunTime() {
        return episodeRunTime;
    }

    public void setEpisodeRunTime(String episodeRunTime) {
        this.episodeRunTime = episodeRunTime;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
}
