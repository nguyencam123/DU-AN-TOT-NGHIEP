// src/app/store.js
import { configureStore } from '@reduxjs/toolkit';
import productReducer from '../features/product/productSlide';
import userReducer from '../features/user/userSlice';
import addproductReducer from '../features/product/createproductslice'
import { AuthMiddleware } from './authMiddleware';
import categoryReducer from '../features/category/categorySlides'


export const store = configureStore({
    reducer: {
        user: userReducer,
        product: productReducer,
        category: categoryReducer,
        addproduct: addproductReducer
    },
    middleware: (getDefaultMiddleware) => [...getDefaultMiddleware()],
});
