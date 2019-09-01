package snsoftware.veegeeiptv.Model;

import java.util.ArrayList;

public class user_account_detail {

        User_info User_infoObject;
        Server_info Server_infoObject;


        // Getter Methods

        public User_info getUser_info() {
            return User_infoObject;
        }

        public Server_info getServer_info() {
            return Server_infoObject;
        }

        // Setter Methods

        public void setUser_info(User_info user_infoObject) {
            this.User_infoObject = user_infoObject;
        }

        public void setServer_info(Server_info server_infoObject) {
            this.Server_infoObject = server_infoObject;
        }
    }

class Server_info {
   private String url;
   private String port;
   private String https_port;
   private String server_protocol;
   private String rtmp_port;
   private String timezone;
   private float timestamp_now;
   private String time_now;


   // Getter Methods

   public String getUrl() {
       return url;
   }

   public String getPort() {
       return port;
   }

   public String getHttps_port() {
       return https_port;
   }

   public String getServer_protocol() {
       return server_protocol;
   }

   public String getRtmp_port() {
       return rtmp_port;
   }

   public String getTimezone() {
       return timezone;
   }

   public float getTimestamp_now() {
       return timestamp_now;
   }

   public String getTime_now() {
       return time_now;
   }

   // Setter Methods

   public void setUrl(String url) {
       this.url = url;
   }

   public void setPort(String port) {
       this.port = port;
   }

   public void setHttps_port(String https_port) {
       this.https_port = https_port;
   }

   public void setServer_protocol(String server_protocol) {
       this.server_protocol = server_protocol;
   }

   public void setRtmp_port(String rtmp_port) {
       this.rtmp_port = rtmp_port;
   }

   public void setTimezone(String timezone) {
       this.timezone = timezone;
   }

   public void setTimestamp_now(float timestamp_now) {
       this.timestamp_now = timestamp_now;
   }

   public void setTime_now(String time_now) {
       this.time_now = time_now;
   }
}

class User_info {
   private String username;
   private String password;
   private String message;
   private float auth;
   private String status;
   private String exp_date;
   private String is_trial;
   private String active_cons;
   private String created_at;
   private String max_connections;
   ArrayList< Object > allowed_output_formats = new ArrayList < Object > ();


   // Getter Methods

   public String getUsername() {
       return username;
   }

   public String getPassword() {
       return password;
   }

   public String getMessage() {
       return message;
   }

   public float getAuth() {
       return auth;
   }

   public String getStatus() {
       return status;
   }

   public String getExp_date() {
       return exp_date;
   }

   public String getIs_trial() {
       return is_trial;
   }

   public String getActive_cons() {
       return active_cons;
   }

   public String getCreated_at() {
       return created_at;
   }

   public String getMax_connections() {
       return max_connections;
   }

   // Setter Methods

   public void setUsername(String username) {
       this.username = username;
   }

   public void setPassword(String password) {
       this.password = password;
   }

   public void setMessage(String message) {
       this.message = message;
   }

   public void setAuth(float auth) {
       this.auth = auth;
   }

   public void setStatus(String status) {
       this.status = status;
   }

   public void setExp_date(String exp_date) {
       this.exp_date = exp_date;
   }

   public void setIs_trial(String is_trial) {
       this.is_trial = is_trial;
   }

   public void setActive_cons(String active_cons) {
       this.active_cons = active_cons;
   }

   public void setCreated_at(String created_at) {
       this.created_at = created_at;
   }

   public void setMax_connections(String max_connections) {
       this.max_connections = max_connections;
   }
}

