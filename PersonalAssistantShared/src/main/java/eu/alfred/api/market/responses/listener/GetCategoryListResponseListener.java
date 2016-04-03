package eu.alfred.api.market.responses.listener;

import eu.alfred.api.market.responses.category.CategoryList;

public interface GetCategoryListResponseListener {
    public void onSuccess(CategoryList item);

    public void onError(Exception exception);
}
