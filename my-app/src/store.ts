// src/store.ts
import { configureStore } from '@reduxjs/toolkit';
import pricesReducer from './pricesSlice';

export const store = configureStore({
    reducer: {
        prices: pricesReducer,
    },
});
