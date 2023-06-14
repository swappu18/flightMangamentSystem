package flight;

public interface Observer {
    void update();

    void setSubject(Subject sub);

    String getMessage();

    int getPid();
}
