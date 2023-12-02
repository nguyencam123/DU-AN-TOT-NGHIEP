import { CheckCircleOutlined } from "@ant-design/icons"
import { Button, Row } from "antd"
import { Content } from "antd/es/layout/layout"


const ComfirmMail = () => {
    return (
        <>
            <Content
                className="site-layout"
                style={{
                    padding: '0 100px',
                    marginTop: '30px',
                    color: 'black'
                }}
            >

                <div style={{ backgroundColor: 'white', width: '50%', height: '500px', marginLeft: '25%', marginTop: '100px', marginBottom: '100px' }}>
                    <Row style={{ marginLeft: '44%', marginBottom: '10px' }}><CheckCircleOutlined style={{ marginTop: '70px', fontSize: '100px', color: 'green' }} /></Row>
                    <Row style={{ marginLeft: '210px', marginBottom: '20px' }}>
                        <h4>Đăng ký thành công</h4>
                    </Row>
                    <Row style={{ marginLeft: '100px', marginBottom: '20px' }}>
                        <div style={{ fontSize: '20px' }}>
                            Bạn hãy nhấn nút bên dưới để kích hoạt tài khoản
                        </div>
                    </Row>
                    <Row style={{ marginLeft: '180px', marginBottom: '30px' }}>
                        <div style={{ fontSize: '20px' }}>Cảm ơn bản đã tin tưởng TraivelVivu</div>
                    </Row>
                    <Row style={{ marginLeft: '185px' }}>
                        <Button style={{ fontSize: '20px', width: '300px', height: '50px', fontWeight: '700', backgroundColor: 'lightblue', color: 'white' }} >Kích hoạt tài khoản</Button>
                    </Row>
                </div>
            </Content>
        </>
    )
}
export default ComfirmMail