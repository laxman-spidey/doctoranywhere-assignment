package co.angel.doctoranywhere.assignment.dataRepository;

public interface ResponseListener {

    void onResponseRecieved(Response response);

    class Response {
        public boolean isOkay;
        public Object data;

        public Response(boolean isOkay, Object data) {
            this.isOkay = isOkay;
            this.data = data;
        }
    }
}