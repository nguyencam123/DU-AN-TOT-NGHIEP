import { useEffect, useState } from 'react'
import { useDispatch, useSelector } from 'react-redux'
import { getProducts } from '../../../features/product/productThunk'
import {
  MDBBtn,
  MDBCard,
  MDBCardBody,
  MDBCardImage,
  MDBCol,
  MDBContainer,
  MDBIcon,
  MDBInput,
  MDBRow,
  MDBTypography,
} from 'mdb-react-ui-kit'
import { useNavigate } from 'react-router-dom'
import { Typography } from 'antd'
import { fetchShoppingCart } from '../../../features/user/shoppingCartThunk'
const { Title } = Typography

export const CartUser = () => {
  const shoppingcart = useSelector((state) => state.shoppingcart.shoppingCart)
  const dispatch = useDispatch()
  const navigate = useNavigate()
  const [loading, setLoading] = useState(false)
  const userDetail = JSON.parse(localStorage.getItem('userDetail'))
  const namelocal = userDetail?.data.id
  useEffect(() => {
    dispatch(fetchShoppingCart(namelocal))
  }, [])
  const handleDetailHomestay = (id) => {
    navigate(`/homestay/detail/${id}`)
  }
  return (
    <section
      style={{
        backgroundColor: '#eee',
        padding: '20px 200px 200px 200px',
        display: 'flex',
      }}
    >
      <div
        style={{
          flex: '60%', // 60% chiều rộng
          marginRight: 20,
        }}
      >
        <div
          style={{
            width: '100%',
            height: '40%',
            backgroundColor: 'white',
            border: '1px solid rgb(213, 217, 226)',
            padding: 10,
            borderRadius: 5,
          }}
        >
          <Title level={3}>Xe đẩy hàng của quý khách (1)</Title>
        </div>
        <br />
        {shoppingcart?.map((items) => {
          return (
            <div
              style={{
                width: '100%',
                height: '100%',
                backgroundColor: 'white',
                border: '1px solid rgb(213, 217, 226)',
                padding: 10,
                borderRadius: 5,
              }}
            >
              <Title level={3}>Xe đẩy hàng của quý khách (1)</Title>
            </div>
          )
        })}
      </div>
      <div
        style={{
          flex: '35%', // 40% chiều rộng
        }}
      >
        <div
          style={{
            width: '100%',
            height: '90%',
            backgroundColor: 'white',
            border: '1px solid rgb(213, 217, 226)',
            padding: 10,
            borderRadius: 5,
          }}
        >
          <Title level={5}>Tổng giá</Title>
          <span>Chưa chọn món hàng nào</span>
          <MDBBtn
            type='submit'
            className='w-100 mb-4'
            size='md'
            style={{ marginTop: 10 }}
            disabled={loading}
          >
            {loading ? 'Đang lưu...' : 'Tiếp theo'}
          </MDBBtn>
        </div>
      </div>
    </section>
  )
}
