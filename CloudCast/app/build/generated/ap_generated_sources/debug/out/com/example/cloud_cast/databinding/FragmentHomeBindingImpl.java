package com.example.cloud_cast.databinding;
import com.example.cloud_cast.R;
import com.example.cloud_cast.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentHomeBindingImpl extends FragmentHomeBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.textView2, 1);
        sViewsWithIds.put(R.id.tableLayout3, 2);
        sViewsWithIds.put(R.id.locationNameTextView, 3);
        sViewsWithIds.put(R.id.thermoIconimageView, 4);
        sViewsWithIds.put(R.id.temperatureTextView, 5);
        sViewsWithIds.put(R.id.textView10, 6);
        sViewsWithIds.put(R.id.highTempTextView, 7);
        sViewsWithIds.put(R.id.lowTempTextView, 8);
        sViewsWithIds.put(R.id.imageView2, 9);
        sViewsWithIds.put(R.id.imageView3, 10);
        sViewsWithIds.put(R.id.windSpeedTextView, 11);
        sViewsWithIds.put(R.id.pressureTextView, 12);
        sViewsWithIds.put(R.id.textView12, 13);
        sViewsWithIds.put(R.id.imageView4, 14);
        sViewsWithIds.put(R.id.cloudiness, 15);
        sViewsWithIds.put(R.id.imageView5, 16);
        sViewsWithIds.put(R.id.textView17, 17);
        sViewsWithIds.put(R.id.textView20, 18);
        sViewsWithIds.put(R.id.imageView6, 19);
        sViewsWithIds.put(R.id.textView21, 20);
        sViewsWithIds.put(R.id.imageView7, 21);
        sViewsWithIds.put(R.id.textView22, 22);
        sViewsWithIds.put(R.id.textView26, 23);
        sViewsWithIds.put(R.id.customListView, 24);
    }
    // views
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentHomeBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 25, sIncludes, sViewsWithIds));
    }
    private FragmentHomeBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.TextView) bindings[15]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[0]
            , (android.widget.ListView) bindings[24]
            , (android.widget.TextView) bindings[7]
            , (android.widget.ImageView) bindings[9]
            , (android.widget.ImageView) bindings[10]
            , (android.widget.ImageView) bindings[14]
            , (android.widget.ImageView) bindings[16]
            , (android.widget.ImageView) bindings[19]
            , (android.widget.ImageView) bindings[21]
            , (android.widget.TextView) bindings[3]
            , (android.widget.TextView) bindings[8]
            , (android.widget.TextView) bindings[12]
            , (android.widget.TableLayout) bindings[2]
            , (android.widget.TextView) bindings[5]
            , (android.widget.TextView) bindings[6]
            , (android.widget.TextView) bindings[13]
            , (android.widget.TextView) bindings[17]
            , (android.widget.TextView) bindings[1]
            , (android.widget.TextView) bindings[18]
            , (android.widget.TextView) bindings[20]
            , (android.widget.TextView) bindings[22]
            , (android.widget.TextView) bindings[23]
            , (android.widget.ImageView) bindings[4]
            , (android.widget.TextView) bindings[11]
            );
        this.constraintLayout.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x1L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
            return variableSet;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        // batch finished
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}