import React from 'react';
import { MDBFooter, MDBContainer, MDBRow, MDBCol, MDBIcon } from 'mdb-react-ui-kit';
import { FacebookOutlined, TwitterOutlined, GoogleOutlined, InstagramOutlined, LinkedinOutlined, GithubOutlined } from '@ant-design/icons'
import logotravel from "../../assets/svg/Rectangle 16.svg"
import logovnpay from "../../assets/svg/Rectangle 20.svg"
export default function FooterUser() {
    return (
        <MDBFooter style={{ backgroundColor: '#5D5D5D', color: 'white' }} className='text-center text-lg-start'>
            <section className='d-flex justify-content-center justify-content-lg-between p-4 border-bottom'>
                <div className='me-5 d-none d-lg-block'>
                    <span>Liên lạc với chúng tôi:</span>
                </div>

                <div>
                    <a href='' className='me-4 text-reset'>
                        <FacebookOutlined style={{ fontSize: '24px' }} />
                    </a>
                    <a href='' className='me-4 text-reset'>
                        <TwitterOutlined style={{ fontSize: '24px' }} />
                    </a>
                    <a href='' className='me-4 text-reset'>
                        <GoogleOutlined style={{ fontSize: '24px' }} />
                    </a>
                    <a href='' className='me-4 text-reset'>
                        <InstagramOutlined style={{ fontSize: '24px' }} />
                    </a>
                    <a href='' className='me-4 text-reset'>
                        <LinkedinOutlined style={{ fontSize: '24px' }} />
                    </a>
                    <a href='' className='me-4 text-reset'>
                        <GithubOutlined style={{ fontSize: '24px' }} />
                    </a>
                </div>
            </section>

            <section className=''>
                <MDBContainer className='text-center text-md-start mt-5'>
                    <MDBRow className='mt-3'>
                        <MDBCol md="3" lg="4" xl="3" className='mx-auto mb-4'>
                            <h6 className='text-uppercase fw-bold mb-4'>
                                <MDBIcon icon="gem" className="me-3" />
                                <img src={logotravel} style={{}} />
                            </h6>
                        </MDBCol>

                        <MDBCol md="2" lg="2" xl="2" className='mx-auto mb-4'>
                            <h6 className='text-uppercase fw-bold mb-4'>Đia điểm</h6>
                            <p>
                                <a href='#!' className='text-reset'>
                                    Hà Nội
                                </a>
                            </p>
                            <p>
                                <a href='#!' className='text-reset'>
                                    TP.Hồ Chí Minh
                                </a>
                            </p>
                            <p>
                                <a href='#!' className='text-reset'>
                                    Vũng Tàu
                                </a>
                            </p>
                            <p>
                                <a href='#!' className='text-reset'>
                                    Đà Lạt
                                </a>
                            </p>
                        </MDBCol>

                        <MDBCol md="3" lg="2" xl="2" className='mx-auto mb-4'>
                            <h6 className='text-uppercase fw-bold mb-4'>Sản Phẩm</h6>
                            <p>
                                <a href='#!' className='text-reset'>
                                    Khách sạn
                                </a>
                            </p>
                            <p>
                                <a href='#!' className='text-reset'>
                                    Dịch Vụ
                                </a>
                            </p>
                            <p>
                                <a href='#!' className='text-reset'>
                                    Ưu đãi
                                </a>
                            </p>
                            <p>
                                <a href='#!' className='text-reset'>
                                    Trợ giúp
                                </a>
                            </p>
                        </MDBCol>

                        <MDBCol md="4" lg="3" xl="3" className='mx-auto mb-md-0 mb-4'>
                            <h6 className='text-uppercase fw-bold mb-4'>Phương thức thanh toán</h6>
                            <p>
                                <MDBIcon icon="home" className="me-2" />
                                <img src={logovnpay} />
                            </p>
                        </MDBCol>
                    </MDBRow>
                </MDBContainer>
            </section>

            <div className='text-center p-4' style={{ backgroundColor: 'rgba(0, 0, 0, 0.05)' }}>
                Mọi nội dung được đảm bảo chính xác và bảo mật. Chịu trách nhiệm bỏi công ty TravelVivu
            </div>
        </MDBFooter>
    );
}