// src/features/user/userSlice.js
import { createSlice } from '@reduxjs/toolkit'

const initialState = {
  user: null,
  isLoggedIn: false,
  admin: null,
  isAdmin: false,
  partner: null,
  ispartner: false,
  userData: null,
  adminData: null,
  partnerData: null,
}

const userSlice = createSlice({
  name: 'user',
  initialState,
  reducers: {
    loginSuccess: (state, action) => {
      state.user = action.payload.user
      state.isLoggedIn = true
      state.userData = action.payload.userData
    },
    adminloginSuccess: (state, action) => {
      state.admin = action.payload.admin
      state.isAdmin = true
      state.adminData = action.payload.adminData
    },
    partnerloginSuccess: (state, action) => {
      state.partner = action.payload.partner
      state.ispartner = true
      state.adminData = action.payload.adminData
    },
    logout: (state) => {
      state.user = null
      state.isLoggedIn = false
      state.admin = null
      state.isAdmin = false
      state.partner = null
      state.ispartner = false
    },
  },
})

export const { loginSuccess, logout, adminloginSuccess, partnerloginSuccess } =
  userSlice.actions
export default userSlice.reducer
