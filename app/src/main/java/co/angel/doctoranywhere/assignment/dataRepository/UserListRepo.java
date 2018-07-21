package co.angel.doctoranywhere.assignment.dataRepository;

import co.angel.doctoranywhere.assignment.dataRepository.RESTServices.UserListRetrofitRepo;

public abstract class UserListRepo {

    public abstract void getUsers(int offset, int limit, final ResponseListener listener);

    private static UserListRepo singleton;

    public static UserListRepo getInstance() {
        if (singleton == null) {
            singleton = new UserListRetrofitRepo();
        }
        return singleton;
    }
}
