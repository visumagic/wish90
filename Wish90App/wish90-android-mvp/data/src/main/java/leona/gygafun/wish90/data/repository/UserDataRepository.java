/**
 * Wish 90
 */
package leona.gygafun.wish90.data.repository;

import leona.gygafun.wish90.data.entity.UserEntity;
import leona.gygafun.wish90.data.entity.mapper.UserEntityDataMapper;
import leona.gygafun.wish90.data.repository.datasource.UserDataStore;
import leona.gygafun.wish90.data.repository.datasource.UserDataStoreFactory;
import leona.gygafun.wish90.domain.User;
import leona.gygafun.wish90.domain.repository.UserRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

/**
 * Created by rkrasniqi on 10/6/15.
 */


/**
 * {@link UserRepository} for retrieving user data.
 */
@Singleton
public class UserDataRepository implements UserRepository {

    private final UserDataStoreFactory userDataStoreFactory;
    private final UserEntityDataMapper userEntityDataMapper;

    /**
     * Constructs a {@link UserRepository}.
     *
     * @param dataStoreFactory A factory to construct different data source implementations.
     * @param userEntityDataMapper {@link UserEntityDataMapper}.
     */
    @Inject
    public UserDataRepository(UserDataStoreFactory dataStoreFactory,
                              UserEntityDataMapper userEntityDataMapper) {
        this.userDataStoreFactory = dataStoreFactory;
        this.userEntityDataMapper = userEntityDataMapper;
    }


    @SuppressWarnings("Convert2MethodRef")
    @Override
    public Observable<List<User>> users() {
        //we always get all users from the cloud
        final UserDataStore userDataStore = this.userDataStoreFactory.createCloudDataStore();
        return userDataStore.userEntityList()
                .map(userEntities -> this.userEntityDataMapper.transform(userEntities));
    }

    @SuppressWarnings("Convert2MethodRef")
    @Override
    public Observable<User> user(int userId) {
        final UserDataStore userDataStore = this.userDataStoreFactory.create(userId);
        return userDataStore.userEntityDetails(userId)
                .map(userEntity -> this.userEntityDataMapper.transform(userEntity));

    }

}
