import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { getProducts } from "../../../features/product/productThunk";
import { Button, Rate, Typography } from "antd";
import { CompassOutlined, ShopOutlined } from "@ant-design/icons";
import { useNavigate } from "react-router-dom";
const { Title } = Typography;


export const CartUser = () => {
  const products = useSelector((state) => state.product.products);
  const dispatch = useDispatch();
  const navigate = useNavigate();
  useEffect(() => {
    dispatch(getProducts());
  }, []);
  const handleDetailHomestay = (id) => {
    navigate(`/homestay/detail/${id}`);
  }
  return (
    <>
      <div
        className="site-layout"
        style={{
          padding: '0 100px',
          marginTop: '30px',
          color: 'black'
        }}
      >
        <div
          style={{
            paddingRight: 100,
            paddingBottom: 50,
            paddingLeft: 100,
            minHeight: 380

          }}
        >
          <h3>Danh sách đã lưu</h3>
          <div>Nơi lưu giữ những homestay yêu thích của bạn!</div>
          {products.map((items) => (
                  <div style={{
                    width: '100%', height: 210,
                    backgroundColor: '#ffffff', borderRadius: 8,
                    boxShadow: '0 0 3px 1px #ACAEB1', marginTop: 20,
                    padding: '2px 2px 2px 2px', display: 'flex'
                  }}>
                    <div style={{ width: '60%' }}>
                      <img src={items.images[0]?.imgUrl} style={{ borderRadius: 8, height: 150, width: 255 }} />
                      <div style={{ marginTop: 8, display: 'flex' }}>
                        <img src={items.images[1]?.imgUrl} style={{ borderRadius: 8, height: 48, marginRight: 6, width: 80 }} />
                        <img src={items.images[2]?.imgUrl} style={{ borderRadius: 8, height: 48, marginRight: 6, width: 80 }} />
                        <img src={items.images[3]?.imgUrl} style={{ borderRadius: 8, height: 48, marginRight: 6, width: 80 }} />
                      </div>
                    </div>
                    <div style={{ width: '50%', marginRight: 50 }}>
                      <h1 style={{ fontSize: 18, marginTop: 10, whiteSpace: 'nowrap', overflow: 'hidden', textOverflow: 'ellipsis', width: 250 }}>{items?.name}</h1>
                      <Rate allowHalf disabled defaultValue={items.star} size='sm' /><br />
                      <div style={{ display: 'flex', marginTop: 10 }}>
                        <CompassOutlined style={{}} />&ensp;
                        <Title style={{ fontSize: 16, whiteSpace: 'nowrap', overflow: 'hidden', textOverflow: 'ellipsis', width: 250, marginTop: 3 }}>{items.address}</Title></div>
                      <h1 style={{ width: '100%', height: 20, backgroundColor: 'rgb(242, 243, 243)', borderRadius: 8, fontSize: 14, padding: '0 2px 0 2px' }}>Thanh toán trực tuyến</h1>
                    </div>
                    <div style={{ marginLeft: 10, borderLeft: '1px solid #ACAEB1', padding: '8px 8px 2px 2px', width: '40%' }}>
                      <div style={{ display: 'flex', color: 'rgb(5, 165, 105)' }}><ShopOutlined style={{ marginTop: 3, fontSize: 14 }} /> Ưu đãi dành riêng cho bạn...</div>
                      <div style={{ float: 'right' }}>
                        <div style={{ fontSize: 22, color: 'rgb(231, 9, 14)' }}>{items.price} VND</div>
                        <div style={{ fontSize: 12, color: 'rgb(231, 9, 14)' }}>Ngày bạn chọn đã có 10 lượt<br /> đặt</div>
                        <div style={{ fontSize: 22 }}><Button style={{ backgroundColor: 'rgb(231, 9, 14)', color: 'white' }} onClick={() => handleDetailHomestay(items.id)} >Xem phòng</Button></div>
                      </div>
                    </div>
                  </div>
                ))}
        </div>
      </div>
    </>
  )
}