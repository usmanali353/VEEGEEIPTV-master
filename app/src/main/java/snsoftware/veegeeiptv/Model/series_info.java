package snsoftware.veegeeiptv.Model;

import java.util.List;

public class series_info {
        public List<Season> seasons = null;
        public Info info;
        public Episodes episodes;


    public class Info {

        public String name;
        public String cover;
        public String plot;
        public String cast;
        public String director;
        public String genre;
        public String releaseDate;
        public String lastModified;
        public String rating;
        public Float rating5based;
        public List<String> backdropPath = null;
        public String youtubeTrailer;
        public String episodeRunTime;
        public String categoryId;
    }

    public class Season {

        public String airDate;
        public Integer episodeCount;
        public Integer id;
        public String name;
        public Object overview;
        public Integer season_number;
        public String cover;
        public String coverBig;

        public String getAirDate() {
            return airDate;
        }

        public void setAirDate(String airDate) {
            this.airDate = airDate;
        }

        public Integer getEpisodeCount() {
            return episodeCount;
        }

        public void setEpisodeCount(Integer episodeCount) {
            this.episodeCount = episodeCount;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getOverview() {
            return overview;
        }

        public void setOverview(Object overview) {
            this.overview = overview;
        }

        public Integer getSeasonNumber() {
            return season_number;
        }

        public void setSeasonNumber(Integer seasonNumber) {
            this.season_number = seasonNumber;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getCoverBig() {
            return coverBig;
        }

        public void setCoverBig(String coverBig) {
            this.coverBig = coverBig;
        }
    }
}
