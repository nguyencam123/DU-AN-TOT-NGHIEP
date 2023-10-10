import { Typography } from "antd"
import hotelimg from "../../../assets/img/saleHotel.png"
import { SearchOutlined } from "@ant-design/icons";

const { Title } = Typography
const Hotel = () => {
    return (
        <>
            <section style={{
                width: '100%', position: 'relative', height: 300,
                backgroundImage: 'linear-gradient(-180deg,rgba(0,160,255,1),#0770cd)', minHeight: 50, marginBottom: 400
            }}>
                <div style={{ justifyContent: 'center', alignItems: 'center', display: 'flex', bottom: 120, padding: '40px 100px 100px 0' }}>
                    <Title level={2} style={{ color: 'white' }}>Tìm & đặt phòng khách sạn giá rẻ
                        chỉ với <br />3 bước đơn giản!<br /><br />
                        <Title level={4} style={{ color: 'rgba(255,255,255,1)' }}>Khám phá ngay những ưu đãi tốt
                            nhất dành cho<br /> bạn tại TraveVIVU!</Title>
                    </Title>&emsp;&emsp;&emsp;&emsp;
                    <img src={hotelimg} style={{ borderRadius: 8, width: '30%' }} />
                </div>
                <div style={{ justifyContent: 'center', display: 'flex' }}>
                    <div style={{
                        position: 'absolute', width: 960,
                        height: 400, backgroundColor: 'white', bottom: -380,
                        borderRadius: 10, boxShadow: '0 0 3px 3px #ACAEB1', padding: '20px 20px 20px 20px'
                    }}>
                        <Title level={4} style={{ color: "#0194f3", display: 'flex' }} ><SearchOutlined style={{ marginTop: 5 }} /> <div> Hãy tìm kiếm khách sạn quý khách mong muốn</div> </Title>
                    </div>
                </div>

            </section>
        </>
    )
}
export default Hotel