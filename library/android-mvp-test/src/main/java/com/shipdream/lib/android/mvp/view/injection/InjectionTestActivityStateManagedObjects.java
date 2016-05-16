/*
 * Copyright 2016 Kejun Xia
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.shipdream.lib.android.mvp.view.injection;

import com.shipdream.lib.android.mvp.manager.NavigationManager;
import com.shipdream.lib.android.mvp.manager.internal.Forwarder;
import com.shipdream.lib.android.mvp.view.MvcActivity;
import com.shipdream.lib.android.mvp.view.MvcFragment;
import com.shipdream.lib.android.mvp.view.nav.MvcTestActivityNavigation;

import javax.inject.Inject;

public class InjectionTestActivityStateManagedObjects extends MvcActivity {
    @Override
    protected Class<? extends MvcFragment> mapNavigationFragment(String locationId) {
        switch (locationId) {
            case MvcTestActivityNavigation.Loc.D:
                return FragmentD.class;
            default:
                return null;
        }
    }

    @Override
    protected Class<? extends DelegateFragment> getDelegateFragmentClass() {
        return HomeFragment.class;
    }

    public static class HomeFragment extends DelegateFragment {
        @Inject
        private NavigationManager navigationManager;

        @Override
        protected void onStartUp() {
            navigationManager.navigate(this).to(MvcTestActivityNavigation.Loc.D, new Forwarder().clearAll());
        }
    }

}