import { createSlice } from '@reduxjs/toolkit';

const initialState = {
    statisticalByYear: [],
    statisticalByMonth: [],
    loading: false,
    error: null,
};

const statisticalSlice = createSlice({
    name: 'statistical',
    initialState,
    reducers: {
        fetchstatisticalStart: (state) => {
            state.loading = true;
            state.error = null;
        },
        fetchStatisticalByYearSuccess: (state, action) => {
            state.statisticalByYear = action.payload;
            state.loading = false;
            state.error = null;
        },
        fetchStatisticalByMonthSuccess: (state, action) => {
            state.statisticalByMonth = action.payload;
            state.loading = false;
            state.error = null;
        },
        fetchstatisticalFailure: (state, action) => {
            state.loading = false;
            state.error = action.payload;
        },

    },
});

export const { fetchstatisticalStart, fetchStatisticalByYearSuccess, fetchstatisticalFailure, fetchStatisticalByMonthSuccess } = statisticalSlice.actions;
export default statisticalSlice.reducer;