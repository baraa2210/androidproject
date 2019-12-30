package ps.gov.notebookapplication;

public class mainnote {
    String Title ;
    String details;
    String id;
    long date;
    String category;

    public mainnote() {}

    public mainnote(String Title, String details, String id, long date ,String category) {
        this.Title = Title;
        this.details = details;
        this.id = id;
        this.date = date;
        this.category=category;


    }



    public String getTitle() {
        return Title;
    }
    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }


    public String getdetails() {
        return details;
    }
    public void setdetails(String details) {
        this.details = details;
    }


    public long getdate() {
        return date;
    }
    public void setdate(long date) {
        this.date = date;
    }

    public String getcategory() { return category; }
    public void setcategory(String category) {
        this.category = category;
    }
}
