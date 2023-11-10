import axios from '../../app/axiosConfig';
import { fetchProductsFailure } from '../product/productSlide';
import { denineProducts } from '../product/productThunk';

const BASE_URL = '/homestay/get-all?size=999';

export const aproveHomestay = (data) => async (dispatch) => {
  try {
    const response = await axios.post('http://localhost:8080/api/v3/homestay/agree', data);
  } catch (error) {
    dispatch(fetchProductsFailure(error.message));
  }
};


