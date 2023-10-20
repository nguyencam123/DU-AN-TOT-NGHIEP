import React from 'react';
import {
    MDBBtn,
    MDBContainer,
    MDBCard,
    MDBCardBody,
    MDBCardImage,
    MDBRow,
    MDBCol,
    MDBIcon,
    MDBInput
}
    from 'mdb-react-ui-kit';
import logotravel from "../../../assets/svg/Rectangle 16.svg"
import management from "../../../assets/svg/Business-Management-PNG-Image.png"

function LoginAdmin() {
    return (
        <MDBContainer className="my-5">

            <MDBCard>
                <MDBRow className='g-0'>

                    <MDBCol md='6'>
                        <img src={management} style={{ width: '100%' }} />
                    </MDBCol>

                    <MDBCol md='6'>
                        <MDBCardBody className='d-flex flex-column'>

                            <div className='d-flex flex-row mt-2'>
                                <img src={logotravel} />
                            </div>

                            <h5 className="fw-normal my-4 pb-3" style={{ letterSpacing: '1px' }}>Đăng nhập với tài khoản của bạn</h5>

                            <MDBInput wrapperClass='mb-4' label='Email address' id='formControlLg' type='email' size="lg" />
                            <MDBInput wrapperClass='mb-4' label='Password' id='formControlLg' type='password' size="lg" />

                            <MDBBtn className="mb-4 px-5" color='dark' size='lg'>Đăng nhập</MDBBtn>
                            <a className="small text-muted" href="#!">Quên mật khẩu ?</a>
                        </MDBCardBody>
                    </MDBCol>

                </MDBRow>
            </MDBCard>

        </MDBContainer>
    );
}

export default LoginAdmin;