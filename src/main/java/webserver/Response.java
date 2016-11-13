package epipog.webserver;

public class Response {

    private final long id;
	private final int status;
    private final String content;

	// Status: OK
    public Response( long id, String content) {
        this.id = id;
        this.content = content;
		this.status = 200;
    }

	// Status: Error
    public Response( long id, int status, String content) {
        this.id = id;
        this.content = content;
		this.status = status;
    }

    public long getId() {
        return id;
    }

    public int getStatus() {
        return status;
    }

    public String getContent() {
        return content;
    }

}