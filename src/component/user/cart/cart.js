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
import { Checkbox, Radio, Rate, Typography } from 'antd'
import { fetchShoppingCart } from '../../../features/user/shoppingCartThunk'
import { DeleteOutlined, EnvironmentOutlined } from '@ant-design/icons'
const { Title } = Typography

export const CartUser = () => {
  const shoppingcart = useSelector((state) => state.shoppingcart.shoppingCart)
  const desc = ['Tệ', 'Không hài lòng', 'Bình thường', 'Hài lòng', 'Tuyệt vời']
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

  const [selectedRadio, setSelectedRadio] = useState([])
  const [selectedRadioData, setSelectedRadioData] = useState([])
  const handleRadioChange = (e) => {
    const index = e.target.value
    setSelectedRadio(index)
    setSelectedRadioData(shoppingcart[index])
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
            // height: '40%',
            backgroundColor: 'white',
            border: '1px solid rgb(213, 217, 226)',
            padding: 10,
            borderRadius: 5,
          }}
        >
          <Title level={3}>
            Xe đẩy hàng của quý khách ({shoppingcart?.length})
          </Title>
        </div>
        <br />
        {shoppingcart?.length === 0 ? (
          <div style={{ textAlign: 'center', marginTop: 20 }}>
            Xe đẩy hàng của bạn đang trống
          </div>
        ) : (
          shoppingcart?.map((items, index) => {
            const addressArray = items.address.split(',') // Tách chuỗi thành mảng dựa trên dấu phẩy

            // Lấy phần tử thứ tư (index 3) trong mảng
            const city = addressArray.length >= 4 ? addressArray[3].trim() : ''
            const averagePoint =
              items.comment.reduce((sum, comment) => sum + comment.point, 0) /
              items.comment.length
            return (
              <div
                style={{
                  width: '100%',
                  // height: '100%',
                  backgroundColor: 'white',
                  border: '1px solid rgb(213, 217, 226)',
                  padding: 10,
                  borderRadius: 5,
                  marginBottom: 20,
                }}
                key={index}
              >
                <div style={{ display: 'flex' }}>
                  <img src={items.images[0].imgUrl} style={{ width: 150 }} />
                  <div style={{ marginLeft: 5 }}>
                    <Title
                      level={5}
                      style={{
                        overflow: 'hidden',
                        whiteSpace: 'nowrap',
                        textOverflow: 'ellipsis',
                      }}
                    >
                      {items.name}
                    </Title>
                    <div style={{ display: 'flex' }}>
                      <EnvironmentOutlined style={{ marginBottom: 2 }} />
                      <Title
                        level={5}
                        style={{
                          overflow: 'hidden',
                          whiteSpace: 'nowrap',
                          textOverflow: 'ellipsis',
                          marginLeft: 5,
                        }}
                      >
                        {city}
                      </Title>
                    </div>
                    <div style={{ display: 'flex' }}>
                      <div
                        style={{
                          width: 30,
                          height: 30,
                          borderRadius: 8,
                          backgroundColor: 'rgb(62, 108, 234)',
                          textAlign: 'center',
                        }}
                      >
                        {averagePoint ? (
                          <span
                            style={{
                              fontWeight: 500,
                              color: 'white',
                              fontSize: 18,
                            }}
                          >
                            {desc[averagePoint - 1]}
                          </span>
                        ) : (
                          <span
                            style={{
                              fontWeight: 500,
                              color: 'white',
                              fontSize: 18,
                            }}
                          >
                            0
                          </span>
                        )}
                      </div>
                      <Rate
                        style={{ marginLeft: 8 }}
                        tooltips={desc}
                        value={averagePoint}
                        disabled
                      />
                      {averagePoint ? (
                        <span>{desc[averagePoint - 1]}</span>
                      ) : (
                        ''
                      )}
                    </div>
                    <div>
                      <Title level={5}>
                        Có {items.comment.length} đánh giá
                      </Title>
                    </div>
                  </div>
                  <div style={{ marginLeft: 'auto' }}>
                    <DeleteOutlined />
                  </div>
                </div>
                <hr />
                <div>
                  <Radio.Group
                    onChange={handleRadioChange}
                    value={selectedRadio}
                  >
                    <Radio value={index} />
                  </Radio.Group>
                </div>
              </div>
            )
          })
        )}
      </div>
      <div
        style={{
          flex: '35%', // 40% chiều rộng
        }}
      >
        <div
          style={{
            width: '100%',
            // height: '90%',
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
            disabled={loading || selectedRadioData.length === 0}
          >
            {loading ? 'Đang lưu...' : 'Tiếp theo'}
          </MDBBtn>
        </div>
      </div>
    </section>
  )
}
