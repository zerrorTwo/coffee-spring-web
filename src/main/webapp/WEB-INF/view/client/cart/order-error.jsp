<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html lang="vi">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Lỗi Đặt Hàng</title>
        <style>
            .error-container {
                max-width: 600px;
                margin: 50px auto;
                text-align: center;
                padding: 30px;
                background: #fff;
                border-radius: 8px;
                box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            }

            .error-icon {
                color: #dc3545;
                font-size: 48px;
                margin-bottom: 20px;
            }

            .error-title {
                color: #dc3545;
                font-size: 24px;
                margin-bottom: 15px;
            }

            .error-message {
                color: #666;
                margin-bottom: 25px;
                line-height: 1.5;
            }

            .btn-group {
                display: flex;
                justify-content: center;
                gap: 15px;
            }

            .btn {
                padding: 10px 20px;
                border-radius: 5px;
                text-decoration: none;
                font-weight: 500;
                transition: all 0.3s ease;
            }

            .btn-primary {
                background: #007bff;
                color: white;
                border: none;
            }

            .btn-primary:hover {
                background: #0056b3;
            }

            .btn-secondary {
                background: #6c757d;
                color: white;
                border: none;
            }

            .btn-secondary:hover {
                background: #545b62;
            }
        </style>
    </head>

    <body>
        <div class="error-container">
            <div class="error-icon">⚠️</div>
            <h1 class="error-title">Không thể đặt hàng</h1>
            <p class="error-message">${error}</p>
            <div class="btn-group">
                <a href="/cart" class="btn btn-secondary">Quay lại giỏ hàng</a>
            </div>
        </div>
    </body>

    </html>