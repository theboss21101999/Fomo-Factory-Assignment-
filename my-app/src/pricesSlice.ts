// src/pricesSlice.ts
import { createSlice, PayloadAction } from '@reduxjs/toolkit';

interface PriceState {
    prices: any[];
}

const initialState: PriceState = {
    prices: [],
};

const pricesSlice = createSlice({
    name: 'prices',
    initialState,
    reducers: {
        setPrices(state, action: PayloadAction<any[]>) {
            state.prices = action.payload;
        },
    },
});

export const { setPrices } = pricesSlice.actions;
export default pricesSlice.reducer;
