import datetime
import os


def save_static_media_file(incoming_file, resource_path):

    # Generate and id with the currrent timestamp
    imgId = str(int(datetime.datetime.now().timestamp() * 1000))

    # Build filename using the id generated
    filename = imgId + "." + incoming_file.filename.split(".")[-1]

    # Check if folder exists or not in the server
    if not os.path.exists(resource_path):
        os.makedirs(resource_path)

    # Create a file path
    file_path = resource_path + filename

    # Write to a temporary file to prevent incomplete files from being used
    temp_file_path = file_path + "~"
    with open(temp_file_path, "wb") as f:
        f.write(incoming_file.file.read())

    # File has been fully saved to disk move it into place
    os.rename(temp_file_path, file_path)

    return filename
