// src/app/store.js
import { configureStore } from '@reduxjs/toolkit';
import productReducer from '../features/product/productSlide';
import userReducer from '../features/user/userSlice';
import addproductReducer from '../features/product/createproductslice'
import { AuthMiddleware } from './authMiddleware';


export const store = configureStore({
    reducer: {
        user: userReducer,
        product: productReducer,
        addproduct: addproductReducer
    },
    middleware: (getDefaultMiddleware) => [...getDefaultMiddleware()],
});
