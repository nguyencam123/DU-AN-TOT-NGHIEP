import { instance } from '../../app/axiosConfig'
import {
  fetchShoppingCartFail,
  fetchShoppingCartSuccess,
  fetchShoppingCartstart,
} from './shoppingCartSlice'

export const fetchShoppingCart = (id) => async (dispatch) => {
  dispatch(fetchShoppingCartstart())
  try {
    const response = await instance.get(
      `http://localhost:8080/api/v1/cart?userId=${id}&size=99`,
    )
    dispatch(fetchShoppingCartSuccess(response.data.data.data)) // Lấy dữ liệu từ response.data.data
  } catch (error) {
    dispatch(fetchShoppingCartFail(error.message))
  }
}
