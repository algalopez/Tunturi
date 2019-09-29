package com.algalopez.ranking.common;

public interface Mapper<I,O> {

    O mapTo(I i);
    I mapFrom(O o);
}
