package com.syh;

import java.lang.String;
import android.os.RemoteException;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Binder;
import android.os.Parcel;

/**
 *
 * @author sh
 *
 */
public interface IStockQuoteService extends android.os.IInterface {
	/** Local-side IPC implementation stub class. */
	public static abstract class Stub extends android.os.Binder implements
			com.syh.IStockQuoteService {
		private static final java.lang.String DESCRIPTOR = "com.syh.IStockQuoteService";

		/** Construct the stub at attach it to the interface. */
		public Stub() {
			this.attachInterface(this, DESCRIPTOR);
		}

		/**
		 * Cast an IBinder object into an IStockQuoteService interface,
		 * generating a proxy if needed.
		 */
		public static com.syh.IStockQuoteService asInterface(
				android.os.IBinder obj) {
			if ((obj == null)) {
				return null;
			}
			com.syh.IStockQuoteService in = (com.syh.IStockQuoteService) obj
					.queryLocalInterface(DESCRIPTOR);
			if ((in != null)) {
				return in;
			}
			return new com.syh.IStockQuoteService.Stub.Proxy(obj);
		}

		public android.os.IBinder asBinder() {
			return this;
		}

		public boolean onTransact(int code, android.os.Parcel data,
				android.os.Parcel reply, int flags)
				throws android.os.RemoteException {
			switch (code) {
			case INTERFACE_TRANSACTION: {
				reply.writeString(DESCRIPTOR);
				return true;
			}
			case TRANSACTION_getQuote: {
				data.enforceInterface(DESCRIPTOR);
				java.lang.String _arg0;
				_arg0 = data.readString();
				double _result = this.getQuote(_arg0);
				reply.writeNoException();
				reply.writeDouble(_result);
				return true;
			}
			}
			return super.onTransact(code, data, reply, flags);
		}

		private static class Proxy implements com.syh.IStockQuoteService {
			private android.os.IBinder mRemote;

			Proxy(android.os.IBinder remote) {
				mRemote = remote;
			}

			public android.os.IBinder asBinder() {
				return mRemote;
			}

			public java.lang.String getInterfaceDescriptor() {
				return DESCRIPTOR;
			}

			public double getQuote(java.lang.String ticker)
					throws android.os.RemoteException {
				android.os.Parcel _data = android.os.Parcel.obtain();
				android.os.Parcel _reply = android.os.Parcel.obtain();
				double _result;
				try {
					_data.writeInterfaceToken(DESCRIPTOR);
					_data.writeString(ticker);
					mRemote.transact(Stub.TRANSACTION_getQuote, _data, _reply,
							0);
					_reply.readException();
					_result = _reply.readDouble();
				} finally {
					_reply.recycle();
					_data.recycle();
				}
				return _result;
			}
		}

		static final int TRANSACTION_getQuote = (IBinder.FIRST_CALL_TRANSACTION + 0);
	}

	public double getQuote(java.lang.String ticker)
			throws android.os.RemoteException;
}
