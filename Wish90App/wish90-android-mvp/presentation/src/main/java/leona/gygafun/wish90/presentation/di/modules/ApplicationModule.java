/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package leona.gygafun.wish90.presentation.di.modules;

import android.content.Context;
import leona.gygafun.wish90.data.cache.UserCache;
import leona.gygafun.wish90.data.cache.UserCacheImpl;
import leona.gygafun.wish90.data.executor.JobExecutor;
import leona.gygafun.wish90.data.repository.UserDataRepository;
import leona.gygafun.wish90.domain.executor.PostExecutionThread;
import leona.gygafun.wish90.domain.executor.ThreadExecutor;
import leona.gygafun.wish90.domain.repository.UserRepository;
import leona.gygafun.wish90.presentation.AndroidApplication;
import leona.gygafun.wish90.presentation.UIThread;
import leona.gygafun.wish90.presentation.navigation.Navigator;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/**
 * Dagger module that provides objects which will live during the application lifecycle.
 */
@Module
public class ApplicationModule {
  private final AndroidApplication application;

  public ApplicationModule(AndroidApplication application) {
    this.application = application;
  }

  @Provides @Singleton Context provideApplicationContext() {
    return this.application;
  }

  @Provides @Singleton Navigator provideNavigator() {
    return new Navigator();
  }

  @Provides @Singleton ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
    return jobExecutor;
  }

  @Provides @Singleton PostExecutionThread providePostExecutionThread(UIThread uiThread) {
    return uiThread;
  }

  @Provides @Singleton UserCache provideUserCache(UserCacheImpl userCache) {
    return userCache;
  }

  @Provides @Singleton UserRepository provideUserRepository(UserDataRepository userDataRepository) {
    return userDataRepository;
  }
}
