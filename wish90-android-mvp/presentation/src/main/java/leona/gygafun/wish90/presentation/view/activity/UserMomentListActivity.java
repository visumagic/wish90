/**
 * Wish 90: The new age wishing app
 Skeleton Credit: http://fernandocejas.com/2014/09/03/architecting-android-the-clean-way/
 *
 */
package leona.gygafun.wish90.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import leona.gygafun.wish90.presentation.R;
import leona.gygafun.wish90.presentation.di.HasComponent;
import leona.gygafun.wish90.presentation.di.components.DaggerUserComponent;
import leona.gygafun.wish90.presentation.di.components.UserComponent;
import leona.gygafun.wish90.presentation.model.UserMomentModel;
import leona.gygafun.wish90.presentation.view.fragment.UserMomentListFragment;

/**
 * Activity that shows a list of Users.
 */
public class UserMomentListActivity extends BaseActivity implements HasComponent<UserComponent>,
    UserMomentListFragment.UserListListener {

  public static Intent getCallingIntent(Context context) {
    return new Intent(context, UserMomentListActivity.class);
  }

  private UserComponent userComponent;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
    setContentView(R.layout.activity_user_moment_list);

    this.initializeInjector();
  }

  private void initializeInjector() {
    this.userComponent = DaggerUserComponent.builder()
        .applicationComponent(getApplicationComponent())
        .activityModule(getActivityModule())
        .build();
  }

  @Override public UserComponent getComponent() {
    return userComponent;
  }


  @Override public void onUserClicked(UserMomentModel userModel) {
    this.navigator.navigateToUserDetails(this, userModel.getUserID());
  }

}
