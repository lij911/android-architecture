package com.lijing.dev.widget.recyclerview.adapter;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.IntRange;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lijing.dev.widget.recyclerview.BaseViewHolder;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 常用 RecyclerView 的 Adapter
 * 只有一种类型的 item
 *
 * @author lijing
 */
public abstract class BaseCommonAdapter<T, VH extends BaseViewHolder> extends RecyclerView.Adapter<VH> {

    protected int mItemResId;
    protected List<T> mData;
    protected Context mContext;
    protected LayoutInflater mLayoutInflater;
    private LinkedList<OnItemClickListener> mOnItemClickListeners;
    private LinkedList<OnItemLongClickListener> mOnItemLongClickListeners;


    public BaseCommonAdapter(@IdRes int layoutId, @Nullable List<T> data) {
        if (layoutId != 0) {
            mItemResId = layoutId;
        }
        mData = data == null ? new ArrayList<>() : data;
    }

    public BaseCommonAdapter(@IdRes int layoutId) {
        this(layoutId, null);
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        mContext = viewGroup.getContext();
        mLayoutInflater = LayoutInflater.from(mContext);
        return createBaseViewHolder(viewGroup, i);
    }

    @Override
    public void onBindViewHolder(@NonNull VH vh, int i) {
        convert(vh, getItem(i), i);
        onBindClickListeners(vh, i);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    /* view holder 和 view 的创建和关联 */

    protected VH createBaseViewHolder(@NonNull ViewGroup parent, int i) {
        return createBaseViewHolder(getItemView(mItemResId, parent));
    }

    @SuppressWarnings("unchecked")
    protected VH createBaseViewHolder(View view) {
        Class myClass = getClass();
        Class vhClass = null;
        while (vhClass == null && null != myClass) {
            vhClass = getViewHolderGenericClass(myClass);
            myClass = myClass.getSuperclass();
        }
        VH k;
        // 泛型擦除会导致z为null
        if (vhClass == null) {
            k = (VH) new BaseViewHolder(view);
        } else {
            k = createGenericInstance(vhClass, view);
        }
        return k != null ? k : (VH) new BaseViewHolder(view);
    }

    protected View getItemView(@LayoutRes int layoutResId, ViewGroup parent) {
        return mLayoutInflater.inflate(layoutResId, parent, false);
    }

    /**
     * view holder 和 items 的关联
     *
     * @param helper
     * @param item
     * @param position
     */
    protected abstract void convert(VH helper, T item, int position);

    /* data modify function */

    @Deprecated
    public List<T> getData() {
        return mData;
    }

    public final T getItem(@IntRange(from = 0) int pos) {
        if (pos >= 0 && pos < mData.size()) {
            return mData.get(pos);
        }
        return null;
    }

    @SafeVarargs
    public final void addItems(T... items) {
        addItems(mData.size(), items);
    }


    @SafeVarargs
    public final void addItems(@IntRange(from = 0) int startPosition, T... items) {
        if (items.length == 0) {
            return;
        }
        addItems(startPosition, Arrays.asList(items));
    }

    public final void addItems(List<T> items) {
        addItems(mData.size(), items);
    }

    public final void addItems(@IntRange(from = 0) int startPosition, List<T> items) {
        if (items == null || items.size() == 0) {
            return;
        }
        mData.addAll(startPosition, items);
        notifyItemRangeInserted(startPosition, items.size());
    }


    public final void setItem(@IntRange(from = 0) int pos, T item) {
        if (item == null || pos < 0 || pos >= mData.size()) {
            return;
        }
        mData.set(pos, item);
        notifyItemChanged(pos);
    }

    public final void removeItem(T item) {
        removeItem(mData.indexOf(item));
    }

    public final void removeItem(@IntRange(from = 0) int pos) {
        removeItems(pos, 1);
    }

    public final void removeItems(@IntRange(from = 0) int start, int count) {
        if (start < 0 || start + count > mData.size()) {
            return;
        }
        for (int i = start + count - 1; i >= start; i--) {
            mData.remove(i);
        }
        notifyItemRangeRemoved(start, count);
    }

    public final void clearData() {
        int size = mData.size();
        mData.clear();
        notifyItemRangeRemoved(0, size);
    }

    /* listener function */

    private void onBindClickListeners(@NonNull VH vh, int i) {

        if (mOnItemClickListeners != null && !mOnItemClickListeners.isEmpty()) {
            for (OnItemClickListener onItemClickListener : mOnItemClickListeners) {
                vh.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onItemClickListener.onItemClick(vh, view, i);
                    }
                });
            }
        }

        if (mOnItemLongClickListeners != null && !mOnItemLongClickListeners.isEmpty()) {
            for (OnItemLongClickListener onItemLongClickListener : mOnItemLongClickListeners) {
                vh.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        return onItemLongClickListener.onItemLongClick(vh, view, i);
                    }
                });
            }
        }
    }

    public void addOnItemClickListener(OnItemClickListener listener) {
        if (mOnItemClickListeners == null) {
            mOnItemClickListeners = new LinkedList<>();
        }
        mOnItemClickListeners.add(listener);
    }

    public void clearOnItemClickListener() {
        if (mOnItemClickListeners != null) {
            mOnItemClickListeners.clear();
        }
    }

    public void addOnItemLongClickListener(OnItemLongClickListener listener) {
        if (mOnItemLongClickListeners == null) {
            mOnItemLongClickListeners = new LinkedList<>();
        }
        mOnItemLongClickListeners.add(listener);
    }

    public void clearOnItemLongClickListener() {
        if (mOnItemLongClickListeners != null) {
            mOnItemLongClickListeners.clear();
        }
    }

    /* private function */


    private Class getViewHolderGenericClass(Class myClass) {
        Type genericSuperclass = myClass.getGenericSuperclass();
        // ParameterizedType 参数化类型
        if (genericSuperclass instanceof ParameterizedType) {
            // getActualTypeArguments获取参数化类型的数组
            for (Type type : ((ParameterizedType) genericSuperclass).getActualTypeArguments()) {
                if (type instanceof Class) {
                    Class aClass = (Class) type;
                    // 是 BaseViewHolder 的子类
                    if (BaseViewHolder.class.isAssignableFrom(aClass)) {
                        return aClass;
                    }
                } else if (type instanceof ParameterizedType) {
                    Type rawType = ((ParameterizedType) type).getRawType();
                    if (rawType instanceof Class && BaseViewHolder.class.isAssignableFrom((Class<?>) rawType)) {
                        return (Class<?>) rawType;
                    }
                }
            }
        }
        return null;
    }

    @SuppressWarnings({"unchecked", "TryWithIdenticalCatches"})
    private VH createGenericInstance(Class vhClass, View view) {
        try {
            Constructor constructor;
            // inner and unstatic class
            if (vhClass.isMemberClass() && !Modifier.isStatic(vhClass.getModifiers())) {
                constructor = vhClass.getDeclaredConstructor(getClass(), View.class);
                constructor.setAccessible(true);
                return (VH) constructor.newInstance(this, view);
            } else {
                constructor = vhClass.getDeclaredConstructor(View.class);
                constructor.setAccessible(true);
                return (VH) constructor.newInstance(view);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }


    public interface OnItemClickListener<T> {
        void onItemClick(BaseViewHolder viewHolder, View view, int position);
    }

    public interface OnItemLongClickListener<T> {
        boolean onItemLongClick(BaseViewHolder viewHolder, View view, int position);
    }
}
