package com.victtech.model;

import com.victtech.model.entity.ChuFang;
import com.victtech.model.entity.Paginator;

/**
 * Created by Richard on 2018/1/25.
 */

public class ChuFangModel extends BaseModel {
    private Transition data;

    public Transition getData() {
        return data;
    }

    public void setData(Transition data) {
        this.data = data;
    }

    public class Transition{
        private TransitionPaginator paginator;
        private ChuFang[] data;

        public ChuFang[] getData() {
            return data;
        }

        public void setData(ChuFang[] data) {
            this.data = data;
        }

        public TransitionPaginator getPaginator() {
            return paginator;
        }

        public void setPaginator(TransitionPaginator paginator) {
            this.paginator = paginator;
        }
    }

    public class TransitionPaginator{
        private Paginator paginator;

        public Paginator getPaginator() {
            return paginator;
        }

        public void setPaginator(Paginator paginator) {
            this.paginator = paginator;
        }
    }
}
