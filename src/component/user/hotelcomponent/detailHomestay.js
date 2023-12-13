
import React, { useEffect } from 'react';
import { Breadcrumb, Col, Layout, Menu, Row, theme, Rate, Button, Image, Progress, Space } from 'antd';
import { ClockCircleTwoTone, EnvironmentOutlined, FileTextTwoTone, StarTwoTone } from '@ant-design/icons'
import { Table } from 'react-bootstrap';
import { useLocation, useNavigate, useParams } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { fetchProducts, getAvgPoint, getCommentProduct, getOneProduct } from '../../../features/product/productThunk';
import moment from 'moment';

const { Header, Content, Footer } = Layout;


export const DetailHomestay = () => {
  const params = useParams();
  const navigate = useNavigate();
  const dispatch = useDispatch();
  useEffect(() => {
    window.scrollTo(0, 0);
  }, []);
  useEffect(() => {
    dispatch(getAvgPoint(params.id));
    dispatch(getCommentProduct(params.id));
    dispatch(getOneProduct(params.id));
  }, []);
  const location = useLocation();
  const param = new URLSearchParams(location.search);
  const startDate = param?.get('startDate') || '';
  const endDate = param?.get('endDate') || '';
  const detailHomestay = useSelector((state) => state.product.productDetails)
  const comment = useSelector((state) => state.product.commentProduct)
  const avgPoint = useSelector((state) => state.product.avgPoint)

  // const imgHomestay = detailHomestay[0].images
  const handleBookingHomestay = (id) => {
    navigate(`/homestay/booking/${id}?startDate=${startDate}&endDate=${endDate}`)
  }
  const listComment = comment.map((comment, index) => {
    if (index === 2) {
      return false;
    }
    return (
      <Col span={6} style={{ marginRight: '70px' }}>
        <div style={{  minHeight:'80px', width: '270px', marginLeft: '15px', marginBottom:'15px' ,paddingLeft: '5px', fontSize: '12px', fontWeight: '500', boxShadow: '0px 2px 5px rgba(3,18,26,0.15)', borderRadius: '5px' }}>
          <div style={{ marginBottom: '5px', paddingTop: '5px' }}>{comment?.user.name}</div>
          <div style={{ width: '250px', wordWrap:'break-word' }}>{comment?.comment}</div>
        </div>
      </Col>
    )
  });
  return (
    <>
      <div
        style={{
          padding: '0 100px',
          marginTop: '30px',
          color: 'black'
        }}
      >
        <Breadcrumb
          style={{
            margin: '-20px 24px',
            fontSize: '12px'
          }}
        >
          <Breadcrumb.Item>Homestay</Breadcrumb.Item>
          <Breadcrumb.Item>Detail</Breadcrumb.Item>
          <Breadcrumb.Item>{detailHomestay.name}</Breadcrumb.Item>
        </Breadcrumb>
        <div
          style={{
            padding: 24,
            minHeight: 380,

          }}
        >
          <Row>
            <Col>
              <h4>{detailHomestay.name}</h4>
              <span>{detailHomestay.name}</span>
            </Col>
          </Row>
          <Row style={{ marginTop: '10px' }}>
            <Col span={8}>
              <EnvironmentOutlined style={{ fontSize: '10px', alignItems: 'center' }} />
              <span style={{ fontSize: '12px', marginTop: '3px' }}>{detailHomestay.address}</span>
              <div style={{ fontSize: '18px', alignItems: 'center', marginTop: '10px' }}>Tổng số phòng của homestay: {detailHomestay.numberPerson}</div>
            </Col>
            <Col span={6} push={10} >
              <div>
                <div style={{ fontSize: '12px', marginBottom: '0' }}>Giá mỗi phòng mỗi đêm từ</div>
                <div style={{ fontSize: '24', color: 'rgb(255, 94, 31)', lineHeight: '28px', fontWeight: '700', marginTop: '-5px' }}> {detailHomestay.price + detailHomestay.price * 11 / 100}
                  <span style={{ fontSize: '22' }}> VND</span> </div>
              </div>
              <Button onClick={() => handleBookingHomestay(params.id)} style={{ width: '100%', backgroundColor: 'rgb(255, 94, 31)' }}>Chọn phòng</Button>
            </Col>
          </Row>
          <Row style={{ marginTop: '15px' }}>
            <Col span={14}>
              <Image
                style={{ borderRadius: '10px' }}
                width={730}
                height={400}
                src={detailHomestay?.images?.[0]?.imgUrl}
              />
            </Col>
            <Col span={4} style={{ marginLeft: '12px' }}>
              <Image
                style={{ borderRadius: '10px', marginBottom: '10px' }}
                width={250}
                height={128}
                src={detailHomestay?.images?.[1]?.imgUrl}
              />
              <Image
                style={{ borderRadius: '10px', marginTop: '7px' }}
                width={250}
                height={128}
                src={detailHomestay?.images?.[2]?.imgUrl}
              />
              <Image
                style={{ borderRadius: '10px', marginTop: '15px' }}
                width={250}
                height={128}
                src={detailHomestay?.images?.[3]?.imgUrl}
              />
            </Col>
            <Col span={4} push={1}>
              <Image
                style={{ borderRadius: '10px', marginBottom: '10px' }}
                width={250}
                height={128}
                src={detailHomestay?.images?.[4]?.imgUrl}
              />
              <Image
                style={{ borderRadius: '10px', marginTop: '7px' }}
                width={250}
                height={128}
                src={detailHomestay?.images?.[5]?.imgUrl}
              />
              <Image
                style={{ borderRadius: '10px', marginTop: '15px' }}
                width={250}
                height={128}
                src={detailHomestay?.images?.[6]?.imgUrl}
              />
            </Col>
          </Row>
          <Row style={{ marginTop: '10px' }}>
            <Col span={5} style={{ backgroundColor: 'white', borderRadius: '5px', height: '150px' }} >
              <div style={{ margin: '10px 0px' }}>
                <h4 style={{ marginLeft: '15px', marginTop: '10px', fontSize: '16px' }}>Tiện nghi chính</h4>
                <div style={{ marginLeft: '15px', fontSize: '12px', fontWeight: '500' }}>
                  <div>Wifi</div>
                  <div>Pool</div>
                  <div>Thang may</div>
                  <div>Phong Gyms</div>
                </div>
              </div>
            </Col>
            <Col span={18} push={1} style={{ backgroundColor: 'white', borderRadius: '5px', height: '150px' }} >
              <div style={{ margin: '10px 0px' }}>
                <h4 style={{ margin: '10px 0px 15px 15px', fontSize: '16px' }}>Cảm nghĩ của du khách</h4>
                <Row>
                  {listComment}
                </Row>
              </div>
            </Col>

          </Row>
        </div>
      </div>

      <div
        style={{
          padding: '0 100px',
          color: 'black'
        }}
      >
        <div
          style={{
            paddingLeft: 24,
            paddingRight: 24,
            minHeight: 10,
          }}
        >
          <Row style={{ backgroundColor: 'white', borderRadius: '5px', minHeight: '10px' }}>
            <Col span={6} style={{ backgroundImage: 'linear-gradient(92deg, rgba(81, 149, 227, 0.5) -60%, #D6F1FF 40%, rgba(214, 241, 255, 0.7) 100%)' }}>
              <h5 style={{ marginTop: '10px', textAlign: 'center' }}>Chính sách và thông tin chung</h5>
            </Col>
            <Col span={18}>
              <div style={{ marginLeft: '10px', marginTop: '10px' }}>
                <div style={{ lineHeight: '12px', marginTop: '3px' }}><ClockCircleTwoTone style={{ fontSize: '12px' }} /> <b>Thời gian nhận/trả Homestay</b></div>
                <span style={{ lineHeight: '12px', marginLeft: '17px', }}>Giờ nhận phòng: <b>Từ {detailHomestay.timeCheckIn}</b></span>
                <span style={{ lineHeight: '12px', marginLeft: '17px', }}>Giờ trả phòng: <b>Trước {detailHomestay.timeCheckOut}</b></span>
              </div>
              <hr style={{ width: '96%', marginLeft: '2%' }} />
              <div style={{ marginLeft: '10px', marginTop: '10px' }}>
                <div style={{ lineHeight: '12px', marginTop: '3px' }}><ClockCircleTwoTone style={{ fontSize: '12px' }} /><b> Chính sách hủy phòng</b></div>
                <span style={{ lineHeight: '12px', marginLeft: '17px', }}>Việc hủy phòng trước ngày abcxyz sẽ được hoàn toàn miễn phí. Sau ngày abcxyz bạn sẽ phải mất một khoản tiền khi hủy phòng</span>
              </div>
              <hr style={{ width: '96%', marginLeft: '2%' }} />
              <div style={{ marginLeft: '10px', marginTop: '10px' }}>
                <div style={{ lineHeight: '16px', marginTop: '3px' }}><FileTextTwoTone style={{ fontSize: '12px' }} /><b> Chính sách hủy phòng</b></div>
                <div style={{ lineHeight: '16px', marginLeft: '17px', }}>Please note that your children might be charged when check-in at the hotel. Please call the hotel before your check-in date for further information.
                  Vui long luu y, tre em co the bi thu them phi khi nhan phong tai khach san. Vui long lien he khach san truoc khi nhan phong de biet them thong tin chi tiet.</div>
              </div>
              <hr style={{ width: '96%', marginLeft: '2%' }} />
              <div style={{ margin: '10px 15px 0px 10px' }}>
                <div style={{ lineHeight: '12px', margin: '0px 0px 15px 5px' }}><b> Thông tin chung</b></div>
                <Table striped hover size="sm">
                  <tbody>
                    <tr>
                      <td>Thời gian nhận trả phòng</td>
                      <td>Từ {detailHomestay?.timeCheckIn} - trước {detailHomestay?.timeCheckOut}</td>
                    </tr>
                    <tr>
                      <td>Diện tích</td>
                      <td>50m2</td>
                    </tr>
                    <tr>
                      <td>Bữa sáng miễn phí</td>
                      <td>Có</td>
                    </tr>
                    <tr>
                      <td>Số người</td>
                      <td>{detailHomestay?.numberPerson}</td>
                    </tr>
                  </tbody>
                </Table>

              </div>
            </Col>
          </Row>
        </div>
      </div>

      <div
        style={{
          padding: '0 100px',
          color: 'black'
        }}
      >
        <div
          style={{
            marginLeft: 24,
            marginRight: 24,
            minHeight: 10,
            marginTop: 20,
            backgroundColor: 'white'
          }}
        >
          <h4 style={{ marginTop: '15px', marginLeft: '10px' }}>Thêm đánh giá từ khách hàng khác</h4>
          <div style={{ marginLeft: '10px' }}><b>Xếp hạng & Điểm đánh giá chung</b></div>
          <div style={{ marginLeft: '10px', color: 'rgba(104,113,118,1.00)' }}>Từ 1.013 đánh giá của khách đã ở</div>
          <Row style={{ backgroundColor: 'white', borderRadius: '5px', minHeight: '10px', marginTop: '15px' }}>
            <Col span={4} style={{ alignItems: 'center' }}>
              <Space wrap style={{ marginLeft: '40px', marginTop: '10px' }}>
                <Progress type="dashboard" percent={avgPoint * 100 / 5} gapDegree={30} />
              </Space>
            </Col>
            <Col span={14}>
              <Progress percent={30} size="default" aria-label='20' />
              <Progress percent={50} size="default" />
              <Progress percent={70} size="default" />
              <Progress percent={50} size="default" />
              <Progress percent={70} size="default" />
            </Col>
            <Col span={5} push={1}>
              <Rate style={{ fontSize: '18px' }} defaultValue={5} disabled /> <br />
              <Rate style={{ fontSize: '18px' }} defaultValue={4} disabled /> <br />
              <Rate style={{ fontSize: '18px' }} defaultValue={3} disabled /> <br />
              <Rate style={{ fontSize: '18px' }} defaultValue={2} disabled /> <br />
              <Rate style={{ fontSize: '18px' }} defaultValue={1} disabled />
            </Col>
          </Row>
          {comment.map((value) =>
            <Row style={{ margin: '20px 10px 10px 10px', border: '2px solid rgba(242,243,243,1.00)', borderRadius: '5px', minHeight: '70px' }}>
              <Col span={5} >
                <h5 style={{ marginLeft: '15px', marginTop: '10px', textAlign: 'center' }}>{value.user.name}</h5>
              </Col>
              <Col span={18} push={1}>
                <div style={{ backgroundColor: 'rgba(236,248,255,1.00)', width: '100px', marginTop: '15px', borderRadius: '10px' }}>
                  <StarTwoTone style={{ fontSize: '20px', paddingBottom: '5px', paddingLeft: '3px' }} />
                  <span style={{ paddingTop: '10px', fontSize: '16px', marginLeft: '10px' }}>{value.point}/5</span>
                </div>
                <div style={{ fontWeight: '500', marginTop: '10px' }}>
                  {value.comment}
                </div>
                <div style={{ margin: '15px 0' }}>
                  {value.images.map((img) => {
                    <Image
                      style={{ borderRadius: '10px' }}
                      width={85}
                      height={85}
                      src={img}
                    />
                  })}
                </div>
              </Col>
            </Row>
          )}
        </div>
      </div>

      <div
        style={{
          textAlign: 'center', marginBottom: 20
        }}
      >
      </div>
    </>
  )

}